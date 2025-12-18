import { ArrowUp, ArrowDown, MoreVertical } from 'lucide-react'
import { Asset } from '../types'

interface MyAssetsProps {
  assets: Asset[]
}

function MyAssets({ assets }: MyAssetsProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('es-AR', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 2,
    }).format(value)
  }

  return (
    <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-bold text-gray-900 dark:text-white">Mis Activos</h2>
        <button className="px-4 py-2 bg-gray-200 dark:bg-gray-700 text-gray-700 dark:text-gray-300 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-600 transition-colors text-sm">
          + Añadir
        </button>
      </div>

      <div className="space-y-4">
        {assets.map((asset) => (
          <div
            key={asset.id}
            className="border border-gray-200 dark:border-gray-700 rounded-lg p-4 hover:bg-gray-50 dark:hover:bg-gray-700/50 transition-colors"
          >
            <div className="flex justify-between items-start">
              <div className="flex-1">
                <div className="flex items-center gap-2 mb-1">
                  <h3 className="font-bold text-gray-900 dark:text-white">{asset.symbol}</h3>
                  <span className="text-xs text-gray-500 dark:text-gray-400">{asset.type}</span>
                </div>
                <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">{asset.name}</p>
                <p className="text-xs text-gray-500 dark:text-gray-500">{asset.units} unidades</p>
              </div>

              <div className="flex items-center gap-4">
                <div className="text-right">
                  <div className="text-xs text-gray-500 dark:text-gray-400 mb-1">
                    <div>Precio Compra: {formatCurrency(asset.purchasePrice)}</div>
                    <div>Total Compra: {formatCurrency(asset.totalPurchase)}</div>
                    <div>Precio Actual: {formatCurrency(asset.currentPrice)}</div>
                    <div>Total Actual: {formatCurrency(asset.totalCurrent)}</div>
                  </div>
                  <div className={`flex items-center gap-1 mt-2 ${asset.performance >= 0 ? 'text-green-500' : 'text-red-500'}`}>
                    {asset.performance >= 0 ? (
                      <ArrowUp size={16} />
                    ) : (
                      <ArrowDown size={16} />
                    )}
                    <span className="text-sm font-semibold">
                      {asset.performancePercent >= 0 ? '+' : ''}{asset.performancePercent.toFixed(2)}% ({formatCurrency(asset.performance)})
                    </span>
                  </div>
                </div>
                <button className="text-gray-400 hover:text-gray-600 dark:hover:text-gray-300">
                  <MoreVertical size={20} />
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default MyAssets

